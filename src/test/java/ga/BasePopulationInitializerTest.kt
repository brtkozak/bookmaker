package ga

import org.junit.Test


internal class BasePopulationInitializerTest {

    @Test
    fun containsTest () {
        val bigList = listOf<Int>(1,2,3,4,5,6,7)
        val smallList1 = listOf<Int>(1,2,3)
        val smallList2 = listOf<Int>(1,2,3,9)
        val smallList3 = listOf<Int>(19,20,21)

        val b1 = bigList.containsAll(smallList1)
        val b2 = bigList.containsAll(smallList2)
        val b3 = bigList.containsAll(smallList3)


        val x =2
    }

}