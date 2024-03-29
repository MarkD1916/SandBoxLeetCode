fun main(args: Array<String>) {



}

//https://leetcode.com/problems/remove-duplicates-from-sorted-array/?envType=list&envId=ewl7u8si
//removeDuplicates(arrayOf(0,0,1,1,1,2,2,3,3,4).toIntArray()) in main
fun removeDuplicates(nums: IntArray): Int {
    var uniSymbol = nums[0]
    var uniCount = if(nums.isNotEmpty()) 1 else nums.size
    for (i in nums) {
        if(uniSymbol < i) {
            uniSymbol = i
            uniCount++
        }
    }
    println(uniCount)
    return uniCount
}

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 */
//in main
//val l1 = ListNode(0)
//l1.next = ListNode(1)
//l1.next?.next = ListNode(2)
//
//val l2 = ListNode(0)
//l2.next = ListNode(1)
//l2.next?.next = ListNode(2)
//
//addTwoNumbers(l1, l2)
//не мое решение, но оно мне очень понравилось и кажется наиболее удачным
class ListNode(var value: Int) {
    var next: ListNode? = null
}

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    return addTwoNumbers(l1, l2, 0)
}

fun addTwoNumbers(l1: ListNode?, l2: ListNode?, unitItMind: Int): ListNode {
    if (l1?.next != null || l2?.next != null) {
        val sum = (l2?.value ?: 0) + (l1?.value ?: 0) + unitItMind
        return if (sum > 9) {
            val listNode = addTwoNumbers(l1?.next, l2?.next, 1)
            ListNode(sum - 10).apply { next = listNode }
        } else {
            val listNode = addTwoNumbers(l1?.next, l2?.next, 0)
            ListNode(sum).apply { next = listNode }
        }
    } else {
        val sum = (l2?.value ?: 0) + (l1?.value ?: 0) + unitItMind
        return if (sum > 9) {
            ListNode(sum - 10).apply { next = ListNode(1) }
        } else {
            ListNode(sum)
        }
    }
}

// валится на ошибке скорости выполнения, но пока что более хорошего варианта не придумал
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
















