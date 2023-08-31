import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TakeLastWhileIncludingTest {

    @Test
    fun `take last while including 1`() {
        val originalList = listOf(4, 3, 2, 1)
        val result = originalList.takeLastWhileIncluding { it < 4 }
        assertEquals(originalList, result)
    }

    @Test
    fun `take last while including 2`() {
        val result = listOf(3, 3, 2, 1).takeLastWhileIncluding { it < 3 }
        assertEquals(listOf(3, 2, 1), result)
    }

    @Test
    fun `take last while including 3`() {
        val result = listOf(1, 1, 1, 1).takeLastWhileIncluding { it < 1 }
        assertEquals(listOf(1), result)
    }

    @Test
    fun `take while including 4`() {
        val originalList = listOf(1, 1, 1, 1)
        val result = originalList.takeLastWhileIncluding { it < 2 }
        assertEquals(originalList, result)
    }
}
