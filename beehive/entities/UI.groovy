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
import org.gentsim.framework.EntityDescription
import groovy.swing.SwingBuilder
import java.awt.*
import javax.swing.*

ui = new EntityDescription("ui")
ui.hive_temp = 0
ui.nbr_bees =  0
ui.bees_flapping =  0
ui.bee_history =  []
ui.temp_history =  []

// swing specific items
ui.frame =  null
ui.nbr_bees_label =  null
ui.bees_flapping_label =  null
ui.temp_label =  null
ui.time_label =  null
ui.start_pause_button =  null

ui.handleEntityCreated ("bee") { bee ->
  nbr_bees++
  nbr_bees_label.text = nbr_bees.toString() 
}

ui.handleEntityDestroyed ("bee") { bee ->
  nbr_bees--
  nbr_bees_label.text = nbr_bees.toString()
}

// record the temperature whenever the temp changes
ui.handleEntityStateChanged ("hive", "temperature") { hive ->
  hive_temp = hive.temperature
  temp_label.text = hive_temp.toString()
}

// record the number of bees flapping at any given time.
ui.handleEntityStateChanged ("hive", "numberBeesFlapping") { hive ->
  bees_flapping = hive.numberBeesFlapping
  bees_flapping_label.text = bees_flapping.toString()
}

// keep track of history
ui.handleTimeUpdate { time ->
  bee_history << (int)bees_flapping
  temp_history << (int)hive_temp
  // force the graph to redraw - probably a bit brute force.
  time_label.text = time.toString()
  frame.validate()
  frame.repaint()
}

ui.handleEvent ("system.status.shutdown") { evt ->
  frame.dispose()
}

ui.handleEvent ("system.status.startup") { evt ->

  def drawHistoryLine = { p, g, history ->
    def hist = history.size() > 500 ? history[-500..-1] : history
    def prevX = 0; def prevY = p.height
    hist.each { 
      g.drawLine prevX, prevY, prevX + 1, p.height - it
      prevX++
      prevY = p.height - it
    }
  }
  
  frame = new SwingBuilder().frame(title : 'Beehive', 
                      defaultCloseOperation : JFrame.DISPOSE_ON_CLOSE, 
                      show: true,
                      size: [500, 400], 
                      locationRelativeTo: null) {
  
    lookAndFeel("system")
    borderLayout()
    vbox {
      hbox {
        panel { border: titledBorder (title: 'buttons')
          vbox {
            start_pause_button = button (text: 'Start', actionPerformed: {
println "button clicked .... ${start_pause_button.text}"
              if (start_pause_button.text == 'Start') { // paused, start
                start_pause_button.text = 'Pause'
                sendEvent(newEvent("system.command.start"))
              }
              else { // running, pause
                start_pause_button.text = 'Start'
                sendEvent(newEvent("system.command.pause"))
              }
            }
            )
            button (text: 'Stop ', actionPerformed: { 
              sendEvent(newEvent("system.command.shutdown"))
            })
          }
        }
        panel { border: titledBorder (title: 'stats')
          vbox {
            hbox {
              label(text: "           Time: "); time_label = label(text: "")
            }
            hbox {
              label(text: "         # Bees: "); nbr_bees_label = label(text: "0")
            }
            hbox {
              label(text: "# Bees Flapping: "); bees_flapping_label = label(text: "0")
            }
            hbox {
              label(text: "   Current Temp: "); temp_label = label(text: "0")
            }
          }
        }
      }
      panel (new GraphPanel(draw: { p, g ->
        // draw the temp
        g.color = Color.RED
        g.drawChars ("Temperature".toCharArray(), 0, 11, 0, 10)
        drawHistoryLine (p, g, temp_history)
        // draw the bees flapping
        g.color = Color.BLACK
        g.drawChars ("# Flapping".toCharArray(), 0, 10, 0, 30)
        drawHistoryLine (p, g, bee_history)
      })) {
      }
    }
  }
  
  // TODO make this not be terminal call - new thread or something
//  frame.pack()
//  frame.show()
}
