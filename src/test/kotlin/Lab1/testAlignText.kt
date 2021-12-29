import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class testAlignText {
    @Test
    fun emptyStringTest() {
        assertFails { changeAlignText("", 4, Alignment.LEFT) }
    }

    @Test
    fun leftAlignTest() {
        val text = "abcd abcd abc abc ab ab ab a a a"
        val expected = """
            |abcd abcd
            | abc abc 
            |ab ab ab a 
            |a a
        """.trimMargin()
        assertEquals(expected, changeAlignText(text, 9))
    }

    @Test
    fun rightAlignTest() {
        val text = """And I in going, madam, weep o'er my father's death
anew: but I must attend his majesty's command, to
whom I am now in ward, evermore in subjection."""

        val expected = """
And I in going, 
madam, weep o'er
    my father's 
           death
anew: but I must
     attend his 
      majesty's 
     command, to
whom I am now in
 ward, evermore 
  in subjection.
    """.trimMargin()
        assertEquals(expected, changeAlignText(text, 16, Alignment.RIGHT))
    }

    @Test
    fun centerAlignTest() {
        val text = "He hath abandoned his physicians, madam; under whose\n" +
                "practises he hath persecuted time with hope, and\n" +
                "finds no other advantage in the process but only the\n" +
                "losing of hope by time."
        val expected = """
He hath abandoned his 
physicians, madam;  
    under whose     
 practises he hath  
persecuted time with
      hope, and     
  finds no other    
 advantage in the   
process but only the
 losing of hope by  
       time.        
        """.trimMargin()
        assertEquals(expected, changeAlignText(text, 20, Alignment.CENTER))
    }
}