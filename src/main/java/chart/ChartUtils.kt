package chart

import org.jfree.data.xy.XYDataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection

class ChartUtils {

    companion object{
        fun getXYCharDataSet(data : ChartDataSet) : XYDataset {
            val dataset = XYSeriesCollection()
            data.series.forEach {
                val series = XYSeries(it.title)
                it.data.forEach { pair ->
                    series.add(pair.first, pair.second)
                }
                dataset.addSeries(series)
            }
            return dataset
        }
    }

}