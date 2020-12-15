package ga.dfstree

import data.entity.bets.SingleBet

data class Node(
        val bet: SingleBet?,
        val result: Result?,
        var leftChild: Node? = null,
        var rightChild: Node? = null
)

enum class Result {
    WIN, LOSE
}
