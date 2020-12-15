package chart

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.block.BlockBorder
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.chart.title.TextTitle
import org.jfree.data.xy.XYDataset
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.JFrame


class ScatterChart : JFrame(), ChartDrawer {

    val colors = listOf<Color>(Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK, Color.ORANGE, Color.MAGENTA)

    override fun draw(chartDataSet: ChartDataSet) {
        val dataset: XYDataset = ChartUtils.getXYCharDataSet(chartDataSet)
        val chart: JFreeChart = createChart(dataset, chartDataSet)
        val chartPanel = ChartPanel(chart)
        chartPanel.border = BorderFactory.createEmptyBorder(15, 15, 15, 15)
        chartPanel.background = Color.white
        add(chartPanel)
        pack()
        title = chartDataSet.title
        setLocationRelativeTo(null)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        this.isVisible = true
    }

    private fun createChart(dataset: XYDataset, chartDataSet: ChartDataSet): JFreeChart {
        val chart: JFreeChart = ChartFactory.createScatterPlot(
                chartDataSet.title,
                chartDataSet.xLabel,
                chartDataSet.yLabel,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        )
        val renderer = XYLineAndShapeRenderer().apply {
            for (i in chartDataSet.series.indices) {
                setSeriesShapesFilled(i, true)
                setSeriesLinesVisible(i, false)
            }
        }
        val plot: XYPlot = chart.xyPlot.apply {
            setRenderer(renderer)
            backgroundPaint = Color.white
            isRangeGridlinesVisible = true
            rangeGridlinePaint = Color.BLACK
            isDomainGridlinesVisible = true
            domainGridlinePaint = Color.BLACK
        }

        chart.legend.frame = BlockBorder.NONE
        chart.title = TextTitle(
                chartDataSet.title,
                Font("Serif", Font.BOLD, 18)
        )
        return chart
    }

}