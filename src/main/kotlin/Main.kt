import java.util.LinkedList
import java.util.Queue

fun main(args: Array<String>) {
//    val n = 3
//    val edges = arrayOf(
//        arrayListOf(
//            0, 1
//        ).toIntArray(),
//        arrayListOf(
//            0, 2
//        ).toIntArray(),
//        arrayListOf(
//            1, 2
//        ).toIntArray(),
//    )

//    val n = 7
//    val edges = arrayOf(
//        arrayListOf(
//            0, 2
//        ).toIntArray(),
//        arrayListOf(
//            0, 5
//        ).toIntArray(),
//        arrayListOf(
//            2, 4
//        ).toIntArray(),
//        arrayListOf(
//            1, 6
//        ).toIntArray(),
//        arrayListOf(
//            5, 4
//        ).toIntArray(),
//    )

//    val n = 6
//    val edges = arrayOf(
//        arrayListOf(
//            0, 1
//        ).toIntArray(),
//        arrayListOf(
//            2, 3
//        ).toIntArray(),
//        arrayListOf(
//            4, 5
//        ).toIntArray()
//    )
    //expected 12

//    val n = 5
//    val edges = arrayOf(
//        arrayListOf(
//            1, 0
//        ).toIntArray(),
//        arrayListOf(
//            3, 1
//        ).toIntArray(),
//        arrayListOf(
//            0, 4
//        ).toIntArray(),
//        arrayListOf(
//            2, 1
//        ).toIntArray()
//    )
    //result 0
//    val n = 5
//    val edges = arrayOf(
//        arrayOf(0,1).toIntArray(),
//        arrayOf(1,2).toIntArray(),
//        arrayOf(3,1).toIntArray(),
//        arrayOf(4,0).toIntArray(),
//    )

    //[[5,0],[1,0],[10,7],[9,8],[7,2],[1,3],[0,2],[8,5],[4,6],[4,2]]
//    val n = 11
//    val edges = arrayOf(
//        arrayListOf(
//            5, 0
//        ).toIntArray(),
//        arrayListOf(
//            1, 0
//        ).toIntArray(),
//        arrayListOf(
//            10, 7
//        ).toIntArray(),
//        arrayListOf(
//            9, 8
//        ).toIntArray(),
//        arrayListOf(
//            7, 2
//        ).toIntArray(),
//        arrayListOf(
//            1, 3
//        ).toIntArray(),
//        arrayListOf(
//            0, 2
//        ).toIntArray(),
//        arrayListOf(
//            8, 5
//        ).toIntArray(),
//        arrayListOf(
//            4, 6
//        ).toIntArray(),
//        arrayListOf(
//            4, 2
//        ).toIntArray(),
//    )

    //[[13,3],[10,1],[6,2],[7,8],[15,0],[0,2],[9,1],[7,11],[3,0],[3,5],[2,7],[6,17],[12,11],[6,16],[3,4],[14,9],[1,0],[18,2],[1,19]]
//    val n = 20
//    val edges = arrayOf(
//        arrayListOf(
//            13, 3
//        ).toIntArray(),
//        arrayListOf(
//            10, 1
//        ).toIntArray(),
//        arrayListOf(
//            6, 2
//        ).toIntArray(),
//        arrayListOf(
//            7, 8
//        ).toIntArray(),
//        arrayListOf(
//            15, 0
//        ).toIntArray(),
//        arrayListOf(
//            0, 2
//        ).toIntArray(),
//        arrayListOf(
//            9, 1
//        ).toIntArray(),
//        arrayListOf(
//            7, 11
//        ).toIntArray(),
//        arrayListOf(
//            3, 0
//        ).toIntArray(),
//        arrayListOf(
//            3, 5
//        ).toIntArray(),
//        arrayListOf(
//            2, 7
//        ).toIntArray(),
//        arrayListOf(
//            6, 17
//        ).toIntArray(),
//        arrayListOf(
//            12, 11
//        ).toIntArray(),
//        arrayListOf(
//            6, 16
//        ).toIntArray(),
//        arrayListOf(
//            3, 4
//        ).toIntArray(),
//        arrayListOf(
//            14, 9
//        ).toIntArray(),
//        arrayListOf(
//            1, 0
//        ).toIntArray(),
//        arrayListOf(
//            18, 2
//        ).toIntArray(),
//        arrayListOf(
//            1, 19
//        ).toIntArray(),
//    )

//    val n = 12
//    val edges = arrayOf(
//        arrayListOf(
//            2, 6
//        ).toIntArray(), arrayListOf(
//            11, 3
//        ).toIntArray(), arrayListOf(
//            5, 4
//        ).toIntArray(), arrayListOf(
//            9, 6
//        ).toIntArray()
//    )

    val n = 16
    val edges = arrayOf(
        arrayListOf(
            0, 15
        ).toIntArray(),
        arrayListOf(
            1, 14
        ).toIntArray(),
        arrayListOf(
            2, 11
        ).toIntArray(),
        arrayListOf(
            4, 3
        ).toIntArray(),
        arrayListOf(
            5, 15
        ).toIntArray(),
        arrayListOf(
            8, 2
        ).toIntArray(),
        arrayListOf(
            14, 12
        ).toIntArray()
    )
    //[[0,15],[1,14],[2,11],[4,3],[5,15],[8,2],[14,12]]
    //result 110

    val result = countPairs(n, edges)
    println(result)
}

fun countPairs(n: Int, edges: Array<IntArray>): Long {
    val all = ((n * (n - 1)) / 2)
    if (edges.isEmpty()) return all.toLong()
    val remainder = all - edges.size
    if (remainder == 0) return 0L
    val range = (0 until n).toMutableList()
    edges.forEach { it.sort() }
    edges.sortBy { it[0] }

    edges.forEach { e ->
        val isModify = e[1] == range[e[1]]
        if (!isModify) {
            if (e[1] != range[e[1]]) {
                range[e[0]] = range[e[1]]
            } else {
                range[e[1]] = e[0]
            }
        } else if (e[1] != range[e[0]]) {
            range[e[1]] = range[e[0]]
        }
    }
    var count = 0
    val pairs = range.groupingBy { it }.eachCount().values.toList()
    pairs.mapIndexed { index, s ->
        pairs.slice(index + 1 until pairs.size).map { count += s * it }
    }
    return count.toLong()

}


/**
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
([]{}) - тоже валидный вариант
([))(] - не валидный вариант
([]{]) - не валидный
([]{)}
([{}])
([]{([{}])})
([}}])
 */
//Одно из худших решений на литкоде, но оно есть :)))
fun isValid(s: String): Boolean {
    if (s.length % 2 != 0) return false
    val closeList = listOf(')', ']', '}')
    if (s.first() in closeList) return false
    val openList = listOf('(', '[', '{')
    val cache = ArrayDeque<Char>()
    s.forEach {
        if (it in openList) {
            cache.addFirst(it)
            openList.indexOf(it)
        }
        if (it in closeList) {
            if (openList[closeList.indexOf(it)] == cache.firstOrNull()) {
                cache.removeFirst()
            } else {
                return false
            }
        }
    }

    return cache.size == 0
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    nums.apply {
        forEachIndexed { upperIndex, valueUpper ->
            forEachIndexed { bottomIndex, valueBottom ->
                if ((valueUpper + valueBottom) == target && upperIndex != bottomIndex) return arrayOf(
                    upperIndex, bottomIndex
                ).toIntArray()
            }
        }
    }
    throw Exception("No correct value in nums array")
}

//не прокатывает решение :(
fun findAnagrams(s: String, p: String) {
    fun findAnagrams(s: String, p: String): List<Int> {
        val len = p.length - 1
        val sorted = p.toCharArray().sorted().joinToString()
        val result = mutableListOf<Int>()
        s.forEachIndexed { index, _ ->
            if (index > (s.length - len - 1)) return result
            val sortedSubstring = s.substring(IntRange(index, len + index)).toCharArray().sorted().joinToString()
            if (sorted.contentEquals(sortedSubstring)) {
                result.add(index)
            }
        }

        return result
    }
}

fun isPalindrome(x: Int): Boolean = x.toString() == x.toString().reversed()

/**
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
 */

fun romanToInt(s: String): Int {
    var arabic = 0
    val charArray = s.toCharArray()
    charArray.forEachIndexed { index, char ->
        char.apply {
            if (this == 'I') arabic += 1
            if (this == 'V') {
                if (index != 0 && charArray[index - 1] == 'I') {
                    arabic -= 1
                    arabic += 4
                } else {
                    arabic += 5
                }
            }
            if (this == 'X') {
                if (index != 0 && charArray[index - 1] == 'I') {
                    arabic -= 1
                    arabic += 9
                } else {
                    arabic += 10
                }
            }
            if (this == 'L') {
                if (index != 0 && charArray[index - 1] == 'X') {
                    arabic -= 10
                    arabic += 40
                } else {
                    arabic += 50
                }
            }
            if (this == 'C') {
                if (index != 0 && charArray[index - 1] == 'X') {
                    arabic -= 10
                    arabic += 90
                } else {
                    arabic += 100
                }
            }
            if (this == 'D') {
                if (index != 0 && charArray[index - 1] == 'C') {
                    arabic -= 100
                    arabic += 400
                } else {
                    arabic += 500
                }
            }
            if (this == 'M') {
                if (index != 0 && charArray[index - 1] == 'C') {
                    arabic -= 100
                    arabic += 900
                } else {
                    arabic += 1000
                }
            }
        }
    }

    return arabic
}

/**
Write a function to find the longest common prefix string amongst an array of strings.
If there is no common prefix, return an empty string "".

Example:
"fllower", "fllow", "fllight", "" -> fll
"cir","car" -> c
 */

//заметка return @forEach не остановит цикл, а остановит единичную лямбду этого цикла. То есть таким образом можно скипнуть 1 итем
//но не остановить цикл как оператор break в Петоне например, чтобы добиться нужного эффекта можно сделать чет типа такого
/**
var count = 0;
run loop@ {
"hello".forEach {
if(it == 'h')
{
println("Exiting the forEach loop. Count is $count");
return@loop;
}
count++;
}
}
 */
//либо вообще вызвать возврат функции
fun longestCommonPrefix(strs: Array<String>): String {
    var prefix = ""
    val filteredEmptyStringList = strs.filter { it.isNotEmpty() }
    val shortedString = filteredEmptyStringList.minByOrNull { it.length } //strs.sortedBy { it.length }.firstOrNull()
    shortedString?.forEachIndexed { index, char ->
        filteredEmptyStringList.forEach { string ->
            if (string != shortedString) {
                if (string[index] != char) {
                    return prefix
                }
            }
        }
        prefix += char
    }

    return prefix
}
















