import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TakeWhileIncludingTest {

    @Test
    fun `take while including 1`() {
        val originalList = listOf(1, 2, 3, 4)
        val result = originalList.takeWhileIncluding { it < 4 }
        assertEquals(originalList, result)
    }

    @Test
    fun `take while including 2`() {
        val result = listOf(1, 2, 3, 3).takeWhileIncluding { it < 3 }
        assertEquals(listOf(1, 2, 3), result)
    }

    @Test
    fun `take while including 3`() {
        val result = listOf(1, 1, 1, 1).takeWhileIncluding { it < 1 }
        assertEquals(listOf(1), result)
    }

    @Test
    fun `take while including 4`() {
        val originalList = listOf(1, 1, 1, 1)
        val result = originalList.takeWhileIncluding { it < 2 }
        assertEquals(originalList, result)
    }
}