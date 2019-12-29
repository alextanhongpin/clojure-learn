# Metadata


```clojure
(def untrusted (with-meta {:command "clean-table" :subject "users"}
                          {:safe false :io true}))

(println untrusted)
(meta untrusted)
```

Metadata in functions:

```clojure
(defn testing-meta "testing metadata from functions"
  {:safe true :console true}
  []
  (println "hello from meta"))

(meta testing-meta); This does not work.
(meta #'testing-meta)
```
