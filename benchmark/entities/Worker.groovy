import org.gentsim.framework.EntityDescription

// create the entity.
worker = new EntityDescription("worker")

// create attributes
worker.otherWorkers = 0 // number of other workes this one knows about.
worker.stateChanges = 0 // number of state change messages seen.
worker.time = 0         // current time

// handle time updates
worker.handleTimeUpdate { t -> time = t }

// register interest in other entities
worker.handleEntityCreated("worker") { ent -> otherWorkers++ }
//worker.handleEntityStateChanged ("worker", "otherWorkers") { ent -> println "state-changed"; stateChanges++ }
worker.handleEntityStateChanged ("worker", ".*") { ent -> stateChanges++ }
worker.handleEntityDestroyed ("worker") { ent -> otherWorkers-- }

