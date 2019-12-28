# Data Structures

## chars, strings and numbers

```clojure
(.split "hello world" " ")
(.endsWith "program.clj" ".clj")
```

## sequences


```clojure
(first [1 2 3 4 5])
(second [1 2 3 4 5])
(last [1 2 3 4 5])
(cons 1 [2 3 4 5])
```

## lists

```clojure
(list 1 2 3 4 5)
(conj (list 1 2 3 4) 5)
```

Defining list as variables:

```
;; This will evaluate as a function.
(def three-numbers (1 2 3))

;; Use quote to avoid execution
(def three-numbers '(1 2 3))
(apply + three-numbers)
```

## vectors

```clojure
(vector 1 2 3 4 5)
(get [1 2 3 4 5] 1000)
(nth [1 2 3 4 5] 0)
([1 2 3 4 5] 0)

(defn the-vector [1 2 3 4 5])

;; Set the value at index 0 to be 1000.
(assoc the-vector 0 1000)
```

## maps


```clojure
(def the-map {:a 100 :b {:c 200}})
(the-map :a)
(:a the-map)
(get the-map :a)
(get-in the-map [:b :c])
(assoc the-map :a 200)
(dissoc the-map :b)
(assoc-in the-map [:b :c] 20)
(update-in the-map [:b :c] + 100)
```
