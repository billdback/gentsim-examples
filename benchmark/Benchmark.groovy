import org.gentsim.framework.*
import org.gentsim.util.Trace

// create the simulation and specify location of entities, etc.
def benchmark = new Simulation(["entities", "events", "services"], true)

// Create entities and services, setting attribute values.
(1..10).each { benchmark.newEntity("worker") }

// Start the simulation.
Trace.on "statistics"
Trace.on "entities"
benchmark.run(10)


