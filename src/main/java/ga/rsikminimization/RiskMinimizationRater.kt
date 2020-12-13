package ga.rsikminimization

import data.entity.bets.SingleBet
import ga.PopulationRater
import ga.entity.CouponsGroup

class RiskMinimizationRater : PopulationRater() {

    val routes = mutableListOf<List<Node>>()
    var couponsGroup: CouponsGroup? = null

    override fun rateCouponsGroup(couponsGroup: CouponsGroup) {
        this.couponsGroup = couponsGroup.copy()
        buildTree(couponsGroup)
        val result = processRoutes()
        couponsGroup.rate = result
        this.couponsGroup = null
        routes.clear()
    }

    private fun buildTree(couponsGroup: CouponsGroup) {
        var singleBets = mutableListOf<SingleBet>()
        couponsGroup.coupons.forEach {
            it.bets.forEach { bet ->
                singleBets.add(bet.copy())
            }
        }
        singleBets = singleBets.distinctBy { it.id } as MutableList<SingleBet>

        val root = Node(null, null)
        root.leftChild = buildNode(singleBets, Result.WIN)
        root.rightChild = buildNode(singleBets, Result.LOSE)
        dfs(root, mutableListOf())
    }

    private fun buildNode(bets: List<SingleBet>, result: Result): Node? {
        return if (bets.isNotEmpty()) {
            val node = Node(bets[0], result)
            node.leftChild = buildNode(bets.toMutableList().drop(1), Result.WIN)
            node.rightChild = buildNode(bets.toMutableList().drop(1), Result.LOSE)
            node
        } else
            null
    }

    private fun dfs(node: Node, route: MutableList<Node>) {
        route.add(node)
        if(node.leftChild != null && node.rightChild != null) {
            dfs(node.leftChild!!, route.toMutableList())
            dfs(node.rightChild!!, route.toMutableList())
        } else {
            routes.add(route)
        }
    }

    private fun processRoutes(): Double {
        var loseProbability = 0.0

        routes.forEach { nodes ->
            val winBets = nodes.filter { it.result == Result.WIN }.mapNotNull { it.bet }
            var winCash = 0.0
            this.couponsGroup?.coupons?.forEach {
                if (winBets.map { winBet -> winBet.id }.containsAll(it.bets.map { bet -> bet.id })) {
                    winCash += it.getWinCash()
                }
            }

            if (winCash < Main.CONTRIBUTION) {
                var routeProb = 1.0
                nodes.forEach {
                    if (it.bet?.myProb != null) {
                        if (it.result == Result.WIN) {
                            routeProb *= it.bet.myProb
                        } else if (it.result == Result.LOSE) {
                            routeProb *= (1 - it.bet.myProb)
                        }
                    }
                }
                loseProbability += routeProb
            }
        }
        if(loseProbability == 0.0 || loseProbability ==0.1) {
            val x  =2
        }
        return loseProbability
    }

    data class Node(
            val bet: SingleBet?,
            val result: Result?,
            var leftChild: Node? = null,
            var rightChild: Node? = null
    )

    enum class Result {
        WIN, LOSE
    }
}