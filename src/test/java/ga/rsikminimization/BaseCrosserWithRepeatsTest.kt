package ga.rsikminimization

import ga.entity.Coupon
import ga.entity.CouponsGroup
import org.junit.Test


internal class BaseCrosserWithRepeatsTest {

    @Test
    fun test() {
        val parent1Coupons = mutableListOf<Coupon>()
        val parent1 = CouponsGroup()
    }

    @Test
    fun splitTest () {
        val time = "2:20.32"
        val minutesSplit = time.split(":")

        var minutes = 0
        var seconds = 0
        var miniSeconds = 0

        var secondsPart = minutesSplit[0]
        if (minutesSplit.size == 2) {
            minutes = minutesSplit[0].toDouble().toInt()
            secondsPart = minutesSplit[1]
        }

        val secondsSplit = secondsPart.split(".")
        seconds = secondsSplit[0].toInt()
        miniSeconds = secondsSplit[1].toInt()

        val totalMiniSeconds = minutes * 60000 + seconds * 100 + miniSeconds

        val x =2
    }
    @Test
    fun splitTest2 () {
        val time = "20.32"
        val minutesSplit = time.split(":")

        var minutes = 0
        var seconds = 0
        var miniSeconds = 0

        var secondsPart = minutesSplit[0]
        if (minutesSplit.size == 2) {
            minutes = minutesSplit[0].toDouble().toInt()
            secondsPart = minutesSplit[1]
        }

        val secondsSplit = secondsPart.split(".")
        seconds = secondsSplit[0].toInt()
        miniSeconds = secondsSplit[1].toInt()

        val totalMiniSeconds = minutes * 60000 + seconds * 1000 + miniSeconds

        val x =2
    }

}