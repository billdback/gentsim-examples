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
import org.gentsim.framework.ServiceDescription

// This service will return the outside temperature.  The temp will vary
// over the course of the day from 60F to 80F.
outside_temp = new ServiceDescription("outside_temp")

// Returns a temperature based on the time.
outside_temp.method("getTemp") { time -> // time is in 15 minute increments
  // convert time to nearest hour.
  def hour = (int)(time / 4) % 24
  //println "getting temp for time ${time} and hour ${hour}"

  def temp

  switch (hour) {
    case 0..6:    temp = 60; break
    case 7..8:    temp = 65; break
    case 9..10:   temp = 70; break
    case 11..13:  temp = 75; break
    case 14..16:  temp = 80; break
    case 17..19:  temp = 75; break
    case 19..21:  temp = 70; break
    case 22:      temp = 65; break
    case 23..24:  temp = 60; break
  }

  temp

}
