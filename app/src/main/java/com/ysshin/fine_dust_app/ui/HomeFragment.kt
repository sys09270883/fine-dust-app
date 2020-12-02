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
import com.ysshin.fine_dust_app.utils.FineDustConverter
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
        val locationData = LocationUtil.getInstance(requireContext()).getCurrentLocationData()
        val doName = AddressConverter.convert("${locationData?.first()}")
        val siName = "${locationData?.last()}"
        val address = "$doName $siName"
        preferenceManager.saveAddressLine(address)
        preferenceManager.saveDoName(doName)
        preferenceManager.saveSiName(siName)
        viewModel.setAddressLine(address)
    }

    override fun onResume() {
        super.onResume()
        if (!viewModel.needUpdate())
            return

        fetchDustInformation()
        fetchWeatherInformation()
    }

    private fun fetchWeatherInformation() {
        val location = LocationUtil.getInstance(requireContext()).getLocation() ?: return
        val call = viewModel.getWeatherData(location.latitude.toInt(), location.longitude.toInt())

        call.enqueue(object : Callback<WeatherResponse>{
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val weatherResponse = response.body()

                val skyList = weatherResponse?.skyList
                val maxTemperature = weatherResponse?.maxTemperature
                val minTemperature = weatherResponse?.minTemperature
                Log.d("weather", "$skyList")
                Log.d("weather", "$maxTemperature")
                Log.d("weather", "$minTemperature")


            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("weather", "${t.message}")
            }

        })
    }

    private fun fetchDustInformation() {
        val call =
            viewModel.getFineDustData(preferenceManager.getDoName(), preferenceManager.getSiName())

        call.enqueue(object : Callback<DustResponse> {
            override fun onResponse(call: Call<DustResponse>, response: Response<DustResponse>) {
                val dustResponse = response.body()

                val dusts = dustResponse?.dusts
                Log.d("dusts", dusts.toString())
                Log.d("dusts", preferenceManager.getSiName())

                dusts?.let {
                    for (dust in dusts) {
                        if (preferenceManager.getSiName() == dust.cityName) {
                            preferenceManager.apply {
                                saveDataTime(dust.dataTime)
                                saveCityName(dust.cityName)
                                saveSo2Value(dust.so2Value)
                                saveCoValue(dust.coValue)
                                saveO3Value(dust.o3Value)
                                saveNo2Value(dust.no2Value)
                                savePm10Value(dust.pm10Value)
                                savePm25Value(dust.pm25Value)
                            }
                            val pm10Value = dust.pm10Value.toInt()
                            val pm25Value = dust.pm25Value.toInt()
                            val fineDustState =
                                FineDustConverter.convertToFineDustState(pm10Value)
                            val ultraFineDustState =
                                FineDustConverter.convertToFineDustState(pm25Value)
                            viewModel.setFineDustValue(dust.pm10Value.toInt())
                            viewModel.setUltraFineDustValue(dust.pm25Value.toInt())
                            viewModel.setFineDustState(fineDustState)
                            viewModel.setUltraFineDustState(ultraFineDustState)
                            viewModel.setDataTime(dust.dataTime)
                            break
                        }
                    }
                }
            }

            override fun onFailure(call: Call<DustResponse>, t: Throwable) {
                Log.e("dusts", "${t.message}")
            }
        })
    }
}