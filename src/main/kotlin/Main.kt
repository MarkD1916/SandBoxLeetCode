import java.util.LinkedList
import java.util.Queue

fun main(args: Array<String>) {

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

        println(cache)
    }

    return cache.size == 0
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    nums.apply {
        forEachIndexed { upperIndex, valueUpper ->
            forEachIndexed { bottomIndex, valueBottom ->
                if ((valueUpper + valueBottom) == target && upperIndex != bottomIndex) return arrayOf(
                    upperIndex,
                    bottomIndex
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
















