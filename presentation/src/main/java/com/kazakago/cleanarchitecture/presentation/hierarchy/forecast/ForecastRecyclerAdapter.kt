package com.kazakago.cleanarchitecture.presentation.hierarchy.forecast

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.presentation.R
import com.kazakago.cleanarchitecture.presentation.global.adapter.AbsItemViewHolder
import com.kazakago.cleanarchitecture.presentation.global.extension.*
import kotlinx.android.synthetic.main.recycler_forecast_content.view.*
import kotlinx.android.synthetic.main.recycler_forecast_summary.view.*

class ForecastRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private enum class ViewType : IntKey {
        Summary,
        Content;
    }

    var weather: Weather? = null
    var onItemClick: ((forecast: Forecast) -> Unit)? = null

    override fun getItemCount(): Int {
        return weather?.let { it.forecasts.size + 1 } ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ViewType.Summary.value()
            else -> ViewType.Content.value()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (ViewType.values().resolve(viewType)) {
            ViewType.Summary -> SummaryViewHolder(context, parent)
            ViewType.Content -> ContentViewHolder(context, parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (ViewType.values().resolve(getItemViewType(position))) {
            ViewType.Summary -> onBindSummaryViewHolder(holder as SummaryViewHolder)
            ViewType.Content -> onBindContentViewHolder(holder as ContentViewHolder, position - 1)
        }
    }

    private fun onBindSummaryViewHolder(holder: SummaryViewHolder) {
        holder.item = weather
    }

    private fun onBindContentViewHolder(holder: ContentViewHolder, fixedIndex: Int) {
        holder.item = weather?.forecasts?.get(fixedIndex)
    }

    inner class SummaryViewHolder(context: Context, parent: ViewGroup) : AbsItemViewHolder<Weather>(context, parent, R.layout.recycler_forecast_summary) {

        override fun onBind(item: Weather?) {
            itemView.areaTextView.text = item?.location?.area
            itemView.prefectureTextView.text = item?.location?.prefecture
            itemView.cityTextView.text = item?.location?.city
            itemView.publicTimeTextView.text = context.getString(R.string.public_time, item?.publicTime?.formattedDateTimeText(context) ?: "")
        }
    }

    inner class ContentViewHolder(context: Context, parent: ViewGroup) : AbsItemViewHolder<Forecast>(context, parent, R.layout.recycler_forecast_content) {

        init {
            itemView.setOnClickListener {
                item?.let { onItemClick?.invoke(it) }
            }
        }

        override fun onBind(item: Forecast?) {
            itemView.weatherImageView.loadImageUrl(item?.imageUrl)
            itemView.dateLabelTextView.text = item?.dateLabel
            itemView.dateTextView.text = item?.date?.formattedDateText(context)
            itemView.telopTextView.text = item?.telop
            itemView.maxTemperatureTextView.text = context.getString(R.string.temperature_max, item?.maxTemperature?.toString() ?: "--")
            itemView.minTemperatureTextView.text = context.getString(R.string.temperature_min, item?.minTemperature?.toString() ?: "--")
        }
    }

}
