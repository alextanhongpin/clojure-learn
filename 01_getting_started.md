# Chapter 1: Getting Started

## Hello world

```clojure
;; Printing without newline.
(print "hello world")
(pr "hello world")

;; Printing with new line.
(println "hello world")
(prn "hello world")
```

## Check user passwords

```clojure
(def users {"kyle" {:password "secret1" :number-pets 2}
            "siva" {:password "secret2" :number-pets 5}
            "rob" {:password "secret3" :number-pets 10}
            "george" {:password "secret4" :number-pets 8}})

(defn check-login [username password]
  (let [actual-password ((users username) :password)]
    (= actual-password password)))

(check-login "kyle" "secret1")
```

## doc and find-doc

`doc`:

```clojure
(doc +)
-------------------------
clojure.core/+
([] [x] [x y] [x y & more])
  Returns the sum of nums. (+) returns 0. Does not auto-promote
  longs, will throw on overflow. See also: +'
```

`find-doc`:

```clojure
(find-doc "apply")
```


## cond

```clojure
(defn print-equality [x]
  (cond 
    (> x 0) (println "greater")
    (= x 0) (println "equal")
    (< x 0) (println "lesser")))
```

## functions

```clojure
;; Variable function.
(def add (fn [x y] (+ x y)))

;; Macro.
(defn add [x y] (+ x y))

;; Add with more than 2 arity.
(defn add [x y & more] (reduce + (+ x y) more))
```

## let

Find the total number of pets for each user:

```clojure
(def users {"kyle" {:password "secret1" :number-pets 2}
            "siva" {:password "secret2" :number-pets 5}
            "rob" {:password "secret3" :number-pets 10}
            "george" {:password "secret4" :number-pets 8}})

(defn average-pets []
  (let [user-data (vals users)
        number-pets (map :number-pets user-data)
	_ (println "number-pets:" number-pets)
        total (apply + number-pets)]
    (/ total (count users))))
```

Another `let` form:

```clojure
(let [x 1
      y 2
      z (+ x y)]
  (+ x y z))
```

## try/catch/finally and throw

Throwing an exception:

```clojure
(throw (Exception. "this is an error"))
```

This will throw an error:
```clojure
(defn average-pets [users]
  (let [user-data (vals users)
        number-pets (map :number-pets user-data)
        total (apply + number-pets)]
    (/ total (count users))))
```

To catch the error:


```clojure
(defn average-pets [users]
  (try 
    (let [user-data (vals users)
              number-pets (map :number-pets user-data)
              total (apply + number-pets)]
          (/ total (count users)))
  (catch Exception e 
    (println "Error") 0)))
```

Handling zero-division error:
```clojure
(defn division [x y]
  (try (/ x y) 
       (catch Exception e
         (println "Error") 0)))
```

## reader macro

```clojure
'(add 1 2 3 4 5)
```
