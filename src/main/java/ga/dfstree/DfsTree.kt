package ga.dfstree

import data.entity.bets.SingleBet
import ga.entity.CouponsGroup

class DfsTree {

    companion object {
        val routes = mutableListOf<List<Node>>()

        fun buildTree(bets : List<SingleBet>): MutableList<List<Node>> {
            val singleBets = bets.toMutableList()

            val root = Node(null, null)
            root.leftChild = buildNode(singleBets, Result.WIN)
            root.rightChild = buildNode(singleBets, Result.LOSE)
            dfs(root, mutableListOf())
            val result = routes.toMutableList()
            routes.clear()
            return result
        }

        fun buildTree(couponsGroup: CouponsGroup): MutableList<List<Node>> {
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
            val result = routes.toMutableList()
            routes.clear()
            return result
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
            if (node.leftChild != null && node.rightChild != null) {
                dfs(node.leftChild!!, route.toMutableList())
                dfs(node.rightChild!!, route.toMutableList())
            } else {
                routes.add(route)
            }
        }

    }
}



