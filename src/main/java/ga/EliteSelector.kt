package ga

import ga.entity.CouponsGroup

class EliteSelector {

    companion object {
        fun selectElite(population: List<CouponsGroup>): MutableList<CouponsGroup> {
            var temp = population.toMutableList()
            val newPopulation = mutableListOf<CouponsGroup>()
            if (Main.MINIMALIZATION) {
                temp = temp.sortedBy { it.rate } as MutableList<CouponsGroup>
            } else {
                temp = temp.sortByDescending { it.rate } as MutableList<CouponsGroup>
            }
            for(i in 0 until Main.ELITE_COUNT) {
                newPopulation.add(temp[i].copy())
            }
            return newPopulation
        }
    }
}