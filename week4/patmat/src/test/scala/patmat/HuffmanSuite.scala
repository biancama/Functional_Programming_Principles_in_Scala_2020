package patmat

import org.junit._
import org.junit.Assert.assertEquals

class HuffmanSuite {
  import Huffman._

  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
    val t3 = Fork(Leaf('a', 8), Fork(Fork(Leaf('b', 3), Fork(Leaf('c',1),Leaf('d', 1),"cd".toList, 2),"bcd".toList, 5), Fork(Fork(Leaf('e',1),Leaf('f', 1),"ef".toList, 2),Fork(Leaf('g',1),Leaf('h', 1),"gh".toList, 2),"efgh".toList, 4), "bcdefgh".toList, 9), "abcdefgh".toList, 17)
    val t4 = Fork(Leaf('a',8),Fork(Fork(Fork(Leaf('d',1),Leaf('c',1),List('d', 'c'),2),Fork(Leaf('f',1),Leaf('e',1),List('f', 'e'),2),List('d', 'c', 'f', 'e'),4),Fork(Fork(Leaf('h',1),Leaf('g',1),List('h', 'g'),2),Leaf('b',3),List('h', 'g', 'b'),5),List('d', 'c', 'f', 'e', 'h', 'g', 'b'),9),List('a', 'd', 'c', 'f', 'e', 'h', 'g', 'b'),17)
  }


  @Test def `weight of a larger tree (10pts)`: Unit =
    new TestTrees {
      assertEquals(5, weight(t1))
    }


  @Test def `chars of a larger tree (10pts)`: Unit =
    new TestTrees {
      assertEquals(List('a','b','d'), chars(t2))
    }

  @Test def `string2chars hello world`: Unit =
    assertEquals(List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'), string2Chars("hello, world"))


  @Test def `make ordered leaf list for some frequency table (15pts)`: Unit =
    assertEquals(List(Leaf('e',1), Leaf('t',2), Leaf('x',3)), makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))))


  @Test def `combine of some leaf list (15pts)`: Unit = {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assertEquals(List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)), combine(leaflist))
  }

  @Test def `decode very short bits should be done correctly `: Unit =
    new TestTrees {
      assertEquals("bac".toList, decode(t3, "10001010".toList.map(c => c.asDigit)))
    }


  @Test def `encode very short bits should be done correctly `: Unit =
    new TestTrees {
      assertEquals("10001010".toList.map(c => c.asDigit), encode(t3)("bac".toList))
    }

  @Test def `decode and encode a very short text should be identity (10pts)`: Unit =
    new TestTrees {
      assertEquals("ab".toList, decode(t1, encode(t1)("ab".toList)))
    }

  @Test def `convert should produce a correct CodeTable`: Unit =
    new TestTrees {
     val convertTable = convert(t3)
      assertEquals("100".toList.map(c => c.asDigit), convertTable.filter({case (c, l) =>c == 'b' }).head._2)
      assertEquals("1010".toList.map(c => c.asDigit), convertTable.filter({case (c, l) =>c == 'c' }).head._2)

    }

  @Test def `times should produce a List of pair with frequency`: Unit =
    new TestTrees {
      val actualFrequency = times(List('a', 'b', 'a')) sortBy({ case (c, _) => c})
      val expectedFrequency = List(('a', 2), ('b', 1))

      assertEquals(expectedFrequency, actualFrequency)
    }

  @Test def `Huffman coding secret`: Unit =
    new  TestTrees {
      val secret = Huffman.decodedSecret


      assertEquals("huffmanestcool", secret.mkString)
    }

  @Test def `quick encode gives the correct byte sequence (20pts)`: Unit =
    new  TestTrees {
      assertEquals("10001010".toList.map(c => c.asDigit), quickEncode(t3)("bac".toList))
    }


  @Test def `createCodeTree create an optimal code tree`: Unit =
    new  TestTrees {
      val actualCodeTree = createCodeTree("cdaeaaabfbaaghaba".toList)
      assertEquals(t4, actualCodeTree)
    }


  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
