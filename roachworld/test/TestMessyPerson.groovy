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
class TestMessyPerson {

  @Shared sth = new SimulationTestHarness(["../services", "../entities"])

  def "Test handling time updates"() {
    setup:
      def messy = sth.newEntity("messy-person")
      messy.kitchen = sth.newService("kitchen")

    when:
      messy.avg_drop_frequency = 1
      (1..10).each { sth.sendTimeUpdateTo(messy, it) }
    then:
      sth.getEntitiesOfType("dropped-food").size == 10

    when:
      messy.avg_drop_frequency = 0
      (1..10).each { sth.sendTimeUpdateTo(messy, it) }
    then:
      sth.getEntitiesOfType("dropped-food").size == 10 // includes from above

    when:
      messy.avg_drop_frequency = 0.5 // tricky since it's random.
      (1..10).each { sth.sendTimeUpdateTo(messy, it) }
    then:
      // bit of a hack - figure random will give 3 to 7 most times
      sth.getEntitiesOfType("dropped-food").size > 12 && sth.getEntitiesOfType("dropped-food").size < 18
  }

}

