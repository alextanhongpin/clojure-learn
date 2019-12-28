# functions

Function with documentation:

```clojure
(defn add 
"adds x and y"
[x y]
  (+ x y))

(doc add)
```

`pre/post`:

```clojure
(defn add [x y] {:pre [(> x 0) (> y 0)]
                 :post [(> % 0)]} ;; % refers to the return value.
  (+ x y))
```

Modular approach:

```clojure
(defn basic-item-total [price quantity] 
  (* price quantity))

(defn with-line-item-conditions [f price quantity]
  {:pre [(> price 0) (> quantity 0)]
   :post [(> % 1)]}
  (apply f [price quantity]))

(with-line-item-conditions basic-item-total 20 1)
(with-line-item-conditions basic-item-total 0 1)
```

## variadic functions

```clojure
(defn total-all-numbers [& numbers]
  (apply + numbers))
```

## recursive functions 

This will hit stackoverflow when the number gets large (> 1000000):
```clojure
(defn count-down [n]
  (if-not (zero? n)
    (do
      (if (= 0 (rem n 100))
        (println "count-down" n))
      (count-down (dec n)))))
```

Use `recur` to bind the arguments to the same name specified in the parameter list when used in the tail position of a function body:

```clojure
(defn count-down [n]
  (if-not (zero? n)
    (do
      (if (= 0 (rem n 100))
        (println "count-down" n))
      (recur (dec n)))))
```

## mutually recursive

Mutually recursive functions that can blow the stack:
```clojure
(defn cat [n]
  (if-not (zero? n)
    (do
      (if (= 0 (rem n 100))
        (println "cat:" n))
      (hat (dec n)))))


(defn hat [n]
  (if-not (zero? n)
    (do
      (if (= 0 (rem n 100))
        (println "hat:" n))
      (cat (dec n)))))
```

Mutually recursive functions that can be called with `trampoline`:

```clojure
(declare hatt)

(defn catt [n]
  (if-not (zero? n)
    (do
      (if (= 0 (rem n 100))
        (println "cat:" n))
      #(hatt (dec n)))))


(defn hatt [n]
  (if-not (zero? n)
    (do
      (if (= 0 (rem n 100))
        (println "hat:" n))
      #(catt (dec n)))))

(trampoline cat 10000)
```

## higher-order functions

`every?`:
```clojure
(def bools [true true true false])
(every? true? bools)
```

`some`:
```clojure
(some #(= % "rob") ["kyle" "siva" "rob" "celeste"])
(some (fn [u] (= u "rob")) ["kyle" "siva" "rob" "celeste"])
```

`partial`:

```clojure
(defn above-threshold? [threshold number]
  (> number threshold))

(filter (fn [n] (above-threshold? 5 n)) (range 1 10))
(filter (partial above-threshold? 5) (range 1 10))
```
