package com.ysshin.fine_dust_app.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ysshin.fine_dust_app.data.DustResponse
import com.ysshin.fine_dust_app.data.WeatherResponse
import com.ysshin.fine_dust_app.databinding.FragmentHomeBinding
import com.ysshin.fine_dust_app.utils.AddressConverter
import com.ysshin.fine_dust_app.utils.LocationUtil
import com.ysshin.fine_dust_app.utils.PreferenceManager
import com.ysshin.fine_dust_app.viewmodels.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()
    private val preferenceManager: PreferenceManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.setLoading(true)
        /* 현재 주소 확인 */
        val locationData = LocationUtil.getInstance(requireContext()).getCurrentLocationData()
        val doName = AddressConverter.convert("${locationData?.first()}")
        val siName = "${locationData?.last()}"
        val address = "$doName $siName"

        /* 저장된 주소와 현재 주소가 다르다면 주소 갱신 */
        if (preferenceManager.getAddressLine() != address) {
            preferenceManager.saveAddressLine(address)
            preferenceManager.saveDoName(doName)
            preferenceManager.saveSiName(siName)
        }

        /* ViewModel 초기화 */
        viewModel.setDataTime(preferenceManager.getDataTime())
        viewModel.setAddressLine(address)
        viewModel.setAllFineDustInfo(
            preferenceManager.getPm10Value(),
            preferenceManager.getPm25Value()
        )
        viewModel.setMaxTemperature(preferenceManager.getMaxTemperature())
        viewModel.setMinTemperature(preferenceManager.getMinTemperature())
        viewModel.setLoading(false)

        val refreshLayout = binding.container
        refreshLayout.setOnRefreshListener {
            fetchAllInformation()
            refreshLayout.isRefreshing = false
        }
    }

    private fun fetchAllInformation() {
        Log.d("yoonseop", "Update occurs")
        viewModel.setLoading(true)
        fetchDustInformation()
        fetchWeatherInformation()
    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.needUpdate())
            return

        fetchAllInformation()
    }

    private fun fetchWeatherInformation() {
        val location = LocationUtil.getInstance(requireContext()).getLocation() ?: return
        val call = viewModel.getWeatherData(location.latitude.toInt(), location.longitude.toInt())

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val weatherResponse = response.body() ?: return

                val skyList = weatherResponse.skyList
                Log.d("yoonseop", "skyList: $skyList")
                val maxTemperature = weatherResponse.maxTemperature
                val minTemperature = weatherResponse.minTemperature
                preferenceManager.saveMaxTemperature(maxTemperature)
                preferenceManager.saveMinTemperature(minTemperature)
                viewModel.setMaxTemperature(maxTemperature)
                viewModel.setMinTemperature(minTemperature)
                viewModel.setLoading(false)
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("yoonseop", "${t.message}")
                viewModel.setLoading(false)
            }
        })
    }

    private fun fetchDustInformation() {
        val call =
            viewModel.getFineDustData(preferenceManager.getDoName(), preferenceManager.getSiName())

        call.enqueue(object : Callback<DustResponse> {
            override fun onResponse(call: Call<DustResponse>, response: Response<DustResponse>) {
                val dustResponse = response.body() ?: return

                Log.d("yoonseop", "${dustResponse}")
                val dusts = dustResponse.dusts
                for (dust in dusts) {
                    if (preferenceManager.getSiName() == dust.cityName) {
                        preferenceManager.apply {
                            saveDataTime(dust.dataTime)
                            saveCityName(dust.cityName)
                            savePm10Value(dust.pm10Value.toInt())
                            savePm25Value(dust.pm25Value.toInt())
                        }
                        viewModel.setAllFineDustInfo(dust.pm10Value.toInt(), dust.pm25Value.toInt())
                        viewModel.setDataTime(dust.dataTime)
                        break
                    }
                }
                viewModel.setLoading(false)
            }

            override fun onFailure(call: Call<DustResponse>, t: Throwable) {
                Log.e("yoonseop", "${t.message}")
                viewModel.setLoading(false)
            }
        })
    }
}