# Agents

```clojure
(def total-cpu-time (agent 0))

@total-cpu-time
```

## Mutating agents

```clojure
; (send the-agent the-function & more-args)
(send total-cpu-time + 700)
```

## Await and Await-for

```clojure
; (await & the-agents)
; (await-for timeout-in-millis & the-agents)
(def bad-agent (agent 0))
@bad-agent

(send bad-agent / 0)
(agent-errors bad-agent)
(clear-agent-errors bad-agent)
```
