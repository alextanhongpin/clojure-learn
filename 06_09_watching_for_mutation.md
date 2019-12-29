# Watching for mutation

```clojure
(def adi (atom 0))

(defn on-change [the-key the-ref old-value new-value]
  (println "Hey, seeing change from" old-value "to" new-value))

(add-watch adi :adi-watcher on-change)

(swap! adi inc)
(remove-watch adi :adi-watcher)
```
