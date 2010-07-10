import org.gentsim.framework.*
import org.gentsim.util.Trace

// create the simulation and specify location of entities, etc.
def benchmark = new Simulation(["entities", "events", "services"], true)
//benchmark.useJMS("tcp://localhost:61616")

// Create entities and services, setting attribute values.
(1..200).each { benchmark.newEntity("worker") }

Trace.on "statistics"
Trace.off "entities"
Trace.off "events"
Trace.off  "debug"

// Start the simulation.
benchmark.run(100)
//benchmark.run(10)
