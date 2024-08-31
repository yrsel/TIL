package Algorithm.BruteForce.BOJ16637

import java.util.*

class BOJ16637 {

    private lateinit var numbers: IntArray
    private lateinit var operators: CharArray

    private lateinit var selected: BooleanArray
    private var size = 0
    private var result = Int.MIN_VALUE

    fun run() {
        val N = readln().toInt()
        size = N / 2
        numbers = IntArray(size + 1)
        operators = CharArray(size + 1)
        selected = BooleanArray(size + 1)

        readln().forEachIndexed { index, c ->
            if (index % 2 == 0) {
                numbers[index / 2] = c.digitToInt()
            } else {
                operators[index / 2] = c
            }
        }

        findResult(0)

        println(result)
    }

    private fun findResult(idx: Int) {
        if (idx == size) {
            if (isSatisfied()) {
                result = result.coerceAtLeast(getResult())
            }
            return
        }

        selected[idx] = true
        findResult(idx + 1)
        selected[idx] = false
        findResult(idx + 1)
    }

    private fun getResult(): Int {
        val stack = Stack<Int>()
        for (i in size - 1 downTo 0) { // 괄호가 있다면 연산 결과를 스택에 넣어주기 ( 스택사용했기에 뒤에서부터 찾기, 맨 위에 앞 쪽 괄호 연산결과가 오도록 )
            if (selected[i]) stack.push(calculate(operators[i], numbers[i], numbers[i + 1]))
        }

        var num = if (selected[0]) stack.pop() else numbers[0] // 맨 처음이 괄호라면 연산결과를 , 아니라면 맨 앞 숫자를 넣어주기
        for (i in 0..<size) {
            if (!selected[i]) { // 아직 연산을 안했다면
                num = if (!selected[i + 1]) calculate(
                    operators[i],
                    num,
                    numbers[i + 1]
                ) // 다음 연산자가 괄호가 포함된 연산이 아니었다면 그냥 연산
                else calculate(operators[i], num, stack.pop()) // 다음 연산자가 괄호가 포함된 연산이었다면 stack에서 미리 계산한 결과 꺼내서 사용
            }
        }
        return num
    }

    private fun calculate(operator: Char, num: Int, otherNum: Int): Int {
        return when (operator) {
            '*' -> num * otherNum
            '+' -> num + otherNum
            else -> num - otherNum
        }
    }

    private fun isSatisfied(): Boolean {
        for (i in 1..<size) {
            if (selected[i - 1] && selected[i]) return false
        }
        return true
    }

}

fun main() {
    BOJ16637().run()
}