/*
Copyright © 2009 William D. Back
This file is part of gentsim-examples.

    gentsim-examples is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    gentsim-examples is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with gentsim-examples.  If not, see <http://www.gnu.org/licenses/>.
*/
import org.junit.runner.RunWith
import spock.lang.*
import static spock.lang.Predef.*

import org.gentsim.framework.*
import org.gentsim.test.*

@Speck
@RunWith(Sputnik)
class TestDroppedFood {

  @Shared sth = new SimulationTestHarness("../entities")

  def "Test create dropped food" () {
    when:
      def df = sth.newEntity("dropped-food")
    then:
      !df.amount
      df.amount == 0
      df.location == [0, 0]
  }

  def testSetLocation () {
    when:
      def df = sth.newEntity("dropped-food")
    then:
      df.location == [0, 0]

    when:
      df.location = [10, 5]
    then:
      df.location == [10, 5]
  }

  def testTakeBiteEnoughRemaining () {
    when:
      def df = sth.newEntity("dropped-food")
      df.amount = 10
    then:
      df.takeBite(4) == 4
      df.amount == 6
  }

  def testTakeBiteNotEnoughRemaining () {
    when:
      def df = sth.newEntity("dropped-food")
      df.amount = 2
    then:
      sth.hasEntityWithId(df.id)
      df.amount == 2
      df.takeBite(4) == 2
      df.amount == 0
      !sth.hasEntityWithId(df.id)
  }

}

