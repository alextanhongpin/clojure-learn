# Program Flow

## Conditionals

`if`:

```clojure
(if true (println "yes") (println "no"))
(if-not true (println "yes") (println "no"))
```

`cond`:

```clojure
(defn range-info [x]
  (cond 
    (< x 0) (println "negative")
    (= x 0) (println "zero")
    :default (println "positive")))
```

`when` is similar like `if`, but allows multiple expressions:

```clojure
(when true
  (println "hello")
  (println "world"))
```

`and`, `or` and `not`:

```clojure
(if (and true false) (println "yes"))
(if (or true false) (println "yes"))
(if (not false) (println "yes"))
```

## Functional iteration

`loop/recur`:

```clojure
(defn fact-loop [n]
  (loop [current n fact 1]
    (if (= current 1)
      fact
      (recur (dec current) (* fact current)))))
```

`doseq`:

```clojure
(def users ["john" "doe" "ivy"])

(defn run-report [user]
  (println "Running report for" user))

(defn dispatch-reporting-jobs [users]
  (doseq [user users]
    (run-report user)))
```

`dotimes`:

```clojure
(dotimes [x 5]
  (println x))
```

`map`:

```clojure
(defn times-two [x] (* x 2))
(map times-two (range 10))
```

`filter`:

```clojure
(filter odd? (range 10))
```

`reduce`:

```clojure
(reduce * (range 1 20))
```

`for`:

```clojure
(defn chessboard-label []
  (for [alpha "abcdefgh"
        num (range 1 9)]
    (str alpha num)))
```

## Threading Macro

`regular`:

```
;; final-amount = principle * (1 + rate / 100) ^ time-periods
(defn final-amount [principle rate time-periods]
	(* (Math/pow (+ 1 (/ rate 100)) time-periods) principle))
```

`thread-first`: 

```
(defn final-amount-> [principle rate time-periods]
  (-> rate
      (/ 100)
      (+ 1)
      (Math/pow time-periods)
      (* principle)))
```

`thread-last`:

```clojure
;; regular
(defn factorial [n]
  (let [numbers (range 1 (+ 1 n))]
    (apply * numbers)))

;; thread-last
(defn factorial->> [n]
  (->> n
       (+ 1)
       (range 1)
       (apply *)))
```
