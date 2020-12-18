package data.entity.statistics

class WomenAlpeinStatistics  {

    companion object {
        val soelden1 = "{\"tournaments\":[{\"name\":\"soelden_1Z\",\"type\":\"Z\",\"day\":1,\"jumps\":[{\"name\":\"BASSINO Marta\",\"points\":\"1:09.54\"},{\"name\":\"BRIGNONE Federica\",\"points\":\"1:10.12\"},{\"name\":\"VLHOVA Petra\",\"points\":\"1:11.56\"},{\"name\":\"GISIN Michelle\",\"points\":\"1:10.72\"},{\"name\":\"HOLTMANN Mina Fuerst\",\"points\":\"1:10.46\"},{\"name\":\"GOGGIA Sofia\",\"points\":\"1:11.30\"},{\"name\":\"HROVAT Meta\",\"points\":\"1:11.95\"},{\"name\":\"GUT-BEHRAMI Lara\",\"points\":\"1:10.93\"},{\"name\":\"WORLEY Tessa\",\"points\":\"1:12.20\"},{\"name\":\"MOLTZAN Paula\",\"points\":\"1:12.43\"},{\"name\":\"ELLENBERGER Andrea\",\"points\":\"1:13.18\"},{\"name\":\"ROBINSON Alice\",\"points\":\"1:10.72\"},{\"name\":\"MIRADOLI Romane\",\"points\":\"1:13.01\"},{\"name\":\"HECTOR Sara\",\"points\":\"1:12.72\"},{\"name\":\"O BRIEN Nina\",\"points\":\"1:12.98\"},{\"name\":\"TRUPPE Katharina\",\"points\":\"1:11.62\"},{\"name\":\"BRUNNER Stephanie\",\"points\":\"1:11.46\"},{\"name\":\"TKACHENKO Ekaterina\",\"points\":\"1:13.13\"},{\"name\":\"SIEBENHOFER Ramona\",\"points\":\"1:12.84\"},{\"name\":\"LEDECKA Ester\",\"points\":\"1:13.30\"},{\"name\":\"BUCIK Ana\",\"points\":\"1:13.22\"},{\"name\":\"NUFER Priska\",\"points\":\"1:12.86\"},{\"name\":\"NORBYE Kaja\",\"points\":\"1:13.45\"},{\"name\":\"GRITSCH Franziska\",\"points\":\"1:12.37\"},{\"name\":\"GRENIER Valerie\",\"points\":\"1:13.44\"},{\"name\":\"HOLDENER Wendy\",\"points\":\"1:11.26\"},{\"name\":\"TILLEY Alex\",\"points\":\"1:13.34\"}]}]}"

        val statistics = listOf(soelden1)

        fun wrapEventIndexToStatisticsIndex(eventIndex: Int) : Int{
            return when(eventIndex) {
                0 -> 0 // pierwsze zawody (nizny) wez dwa pierwsze elementy
                1 -> 3
                2 -> 5
                3 -> 6
                4 -> 7
                5 -> 8
                else -> 0
            }
        }
    }


}