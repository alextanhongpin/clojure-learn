# Scope

## Vars and binding

```clojure
(def MAX-CONNECTION 10)
```

## Special variables
```clojure
(def ^:dynamic *db-host* "localhost")
(defn expense-report []
  (println *db-host*))

(binding [*db-host* "production"]
  (expense-report))
```

Dynamic scope in action:

```clojure
(def ^:dynamic *eval-me* 10)

(defn print-the-var [label]
  (println label *eval-me*))

(print-the-var "A:")

(binding [*eval-me* 20] ; The first binding.
  (print-the-var "B:")
  (binding [*eval-me* 30] ; The second binding.
    (print-the-var "C:"))
  (print-the-var "D:"))
```

A higher-order function for aspect-oriented logging:

```clojure
(defn ^:dynamic twice [x]
  (println "original function")
  (* 2 x))

(defn call-twice [y]
  (twice y))

(defn with-log [function-to-call log-statement]
  (fn [& args]
  (println log-statement)
  (apply function-to-call args)))

(call-twice 10)
(binding [twice (with-log twice "Calling the twice function")]
  (call-twice 20))

(call-twice 30)
```

## Laziness and special variables

```clojure
(def ^:dynamic *factor* 10)

(defn multiply [x]
  (* x *factor*))

; This will produce (10 20 30 40 50).
(map multiply [1 2 3 4 5])

; This will also produce (10 20 30 40 50).
(binding [*factor* 20]
  (map multiply [1 2 3 4 5]))

; This will also produce (20 40 60 80 100).
(binding [*factor* 20]
  (doall (map multiply [1 2 3 4 5])))
```
