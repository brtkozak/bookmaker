package data.entity.bets

class WomenAlpeinBets {

    companion object {
        val valdisere1 = "{\"bets\":[{\"names\":\"Goggia S. - Johnson B.\",\"odd1\":\"1.80\",\"odd2\":\"1.88\",\"won\":1},{\"names\":\"Goggia S. - Suter C.\",\"odd1\":\"1.50\",\"odd2\":\"2.38\",\"won\":2},{\"names\":\"Goggia S. - Gut Behrami L.\",\"odd1\":\"1.35\",\"odd2\":\"2.89\",\"won\":1},{\"names\":\"Goggia S. - Ledecká E.\",\"odd1\":\"1.32\",\"odd2\":\"3.04\",\"won\":1},{\"names\":\"Johnson B. - Suter C.\",\"odd1\":\"1.55\",\"odd2\":\"2.26\",\"won\":2},{\"names\":\"Johnson B. - Gut Behrami L.\",\"odd1\":\"1.42\",\"odd2\":\"2.61\",\"won\":1},{\"names\":\"Johnson B. - Lie K.V.\",\"odd1\":\"1.38\",\"odd2\":\"2.76\",\"won\":1},{\"names\":\"Johnson B. - Ledecká E.\",\"odd1\":\"1.38\",\"odd2\":\"2.76\",\"won\":1},{\"names\":\"Suter C. - Gut Behrami L.\",\"odd1\":\"1.60\",\"odd2\":\"2.16\",\"won\":1},{\"names\":\"Suter C. - Brignone F.\",\"odd1\":\"1.50\",\"odd2\":\"2.38\",\"won\":0},{\"names\":\"Suter C. - Schmidhofer N.\",\"odd1\":\"1.50\",\"odd2\":\"2.38\",\"won\":1},{\"names\":\"Gut Behrami L. - Lie K.V.\",\"odd1\":\"1.80\",\"odd2\":\"1.88\",\"won\":1},{\"names\":\"Gut Behrami L. - Ledecká E.\",\"odd1\":\"1.80\",\"odd2\":\"1.88\",\"won\":2},{\"names\":\"Gut Behrami L. - McKennis A.\",\"odd1\":\"1.45\",\"odd2\":\"2.52\",\"won\":0},{\"names\":\"Brignone F. - Schmidhofer N.\",\"odd1\":\"1.84\",\"odd2\":\"1.84\",\"won\":0},{\"names\":\"Brignone F. - Ortlieb N.\",\"odd1\":\"1.44\",\"odd2\":\"2.47\",\"won\":2},{\"names\":\"Ledecká E. - Schmidhofer N.\",\"odd1\":\"1.70\",\"odd2\":\"2.01\",\"won\":0},{\"names\":\"Ledecká E. - McKennis A.\",\"odd1\":\"1.48\",\"odd2\":\"2.43\",\"won\":0},{\"names\":\"Ortlieb N. - Vlhova P.\",\"odd1\":\"1.55\",\"odd2\":\"2.20\",\"won\":1},{\"names\":\"Vlhova P. - Marsaglia F.\",\"odd1\":\"1.67\",\"odd2\":\"2.05\",\"won\":2},{\"names\":\"Vlhova P. - Venier S.\",\"odd1\":\"1.72\",\"odd2\":\"1.98\",\"won\":2}]}"

        val bets = listOf(valdisere1)
        var lastTournament = "valdisere"

        fun setLastTournament(index : Int) {
            when(index) {
                0 -> lastTournament = "valdisere"
                1 -> lastTournament = "valdisere"
            }
        }
    }
}