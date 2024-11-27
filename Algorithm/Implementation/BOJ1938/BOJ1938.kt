package Algorithm.Implementation.BOJ1938

class BOJ1938 {
    private var n = 0
    private lateinit var map: Array<IntArray>
    private lateinit var visited: Array<Array<BooleanArray>>
    private val dir = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))

    private data class Pos(val r: Int, val c: Int, val dir: Int, val cnt: Int = 0)

    private var from: Pos? = null
    private var to: Pos? = null

    fun solve() {
        n = readln().toInt()
        map = init(n)
        visited = Array(2) { Array(n) { BooleanArray(n) } }
        println(bfs())
    }

    private fun init(size: Int): Array<IntArray> {
        return Array(size) { r ->
            val input = readln()
            input.mapIndexed { c, char ->
                when (char) {
                    '0' -> 0
                    '1' -> -1
                    'B' -> {
                        from = from ?: if (c == size - 1 || input[c + 1] != 'B') Pos(r + 1, c, 1) // 세로
                        else Pos(r, c + 1, 0) // 가로
                        0
                    }

                    else -> {
                        to = to ?: if (c == size - 1 || input[c + 1] != 'E') Pos(r + 1, c, 1) // 세로
                        else Pos(r, c + 1, 0) // 가로
                        0
                    }
                }
            }.toIntArray()
        }
    }

    private fun bfs(): Int {
        val q = ArrayDeque<Pos>()
        q.addFirst(from!!)
        val cur = q.first()
        visited[cur.dir][cur.r][cur.c] = true

        val des = to!!
        while (q.isNotEmpty()) {
            val curr = q.removeFirst()
            if (curr.r == des.r && curr.c == des.c && curr.dir == des.dir) {
                return curr.cnt
            }
            for (d in 0..<4) {
                val nr = curr.r + dir[d][0]
                val nc = curr.c + dir[d][1]
                if (!isIn(nr, nc) || visited[curr.dir][nr][nc] || map[nr][nc] != 0) continue
                when (d) {
                    0 -> if (isUp(nr, nc, curr.dir)) q.offer(Pos(nr, nc, curr.dir, curr.cnt + 1))
                    1 -> if (isDown(nr, nc, curr.dir)) q.offer(Pos(nr, nc, curr.dir, curr.cnt + 1))
                    2 -> if (isLeft(nr, nc, curr.dir)) q.offer(Pos(nr, nc, curr.dir, curr.cnt + 1))
                    3 -> if (isRight(nr, nc, curr.dir)) q.offer(Pos(nr, nc, curr.dir, curr.cnt + 1))
                }
            }
            if (isTurn(curr.r, curr.c, curr.dir)) {
                q.offer(Pos(curr.r, curr.c, if (curr.dir == 0) 1 else 0, curr.cnt + 1))
            }
        }
        return 0
    }

    private fun isIn(r: Int, c: Int) = r in 0..<n && c in 0..<n

    private fun isUp(r: Int, c: Int, dir: Int): Boolean {
        if (dir == 0) return map[r][c] == 0 && map[r][c - 1] == 0 && map[r][c + 1] == 0
        return isIn(r - 1, c) && map[r - 1][c] == 0
    }

    private fun isDown(r: Int, c: Int, dir: Int): Boolean {
        if (dir == 0) return map[r][c] == 0 && map[r][c - 1] == 0 && map[r][c + 1] == 0
        return isIn(r + 1, c) && map[r + 1][c] == 0
    }

    private fun isLeft(r: Int, c: Int, dir: Int): Boolean {
        if (dir == 0) return isIn(r, c - 1) && map[r][c - 1] == 0
        return map[r][c] == 0 && map[r - 1][c] == 0 && map[r + 1][c] == 0
    }

    private fun isRight(r: Int, c: Int, dir: Int): Boolean {
        if (dir == 0) return isIn(r, c + 1) && map[r][c + 1] == 0
        return map[r][c] == 0 && map[r - 1][c] == 0 && map[r + 1][c] == 0
    }

    private fun isTurn(r: Int, c: Int, dir: Int): Boolean {
        if (dir == 0) return isIn(r + 1, c) && isIn(r - 1, c) && isUp(r + 1, c, dir) && isDown(r - 1, c, dir)
        return isIn(r, c - 1) && isIn(r, c + 1) && isLeft(r, c - 1, dir) && isRight(r, c + 1, dir)
    }

    private fun ArrayDeque<Pos>.offer(element: Pos) {
        if (visited[element.dir][element.r][element.c]) return
        visited[element.dir][element.r][element.c] = true
        this.addLast(element)
    }
}

fun main() {
    BOJ1938().solve()
}