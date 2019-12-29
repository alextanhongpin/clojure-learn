# Destructuring

## Hash map

```clojure
; Without destructuring.
(defn describe-salary [person]
  (let [first (:first-name person)
        last (:last-name person)
        annual (:salary person)]
    (println first last "earns" annual)))

; With destructuring.
(defn describe-salary [{first :first-name
                        last :last-name
                        annual :salary}]
  (println first last "earns" annual))
```

## Vector bindings

```clojure
(defn print-amounts [[amount-1 amount-2]]
  (println "amounts are:" amount-1 "and" amount-2))

(print-amounts [10.34 43.2])
```

Using `&` and `:as`:

```clojure
(defn print-amounts-multiple [[amount-1 amount-2 & remaining :as all]]
  (println "amounts are:" amount-1 "and" amount-2)
  (println "remaining:" remaining)
  (println "all:", all))

(print-amounts-multiple [10.34 43.2 30.2 54.3])
```

Nested vectors:

```clojure
(defn print-first-category [[[category amount] & _]]
  (println "First category is:" category)
  (println "First amount is:" amount))

(def expenses [[:books 48.3] [:coffee 2.3] [:caltrain 32.3]])
(print-first-category expenses)
```

## Map bindings

```clojure
(defn describe-salary [{first :first-name
                         last :last-name
                         annual :salary
                         bonus :bonus-percentage
                         :or {bonus 5}
                         :as p}]
  (println first last "earns" annual "with a" bonus "percent-bonus")
  (println p))

(def a-user {:first-name "john"
             :last-name "doe"
             :salary 1000
             :bonus-percentage 15})
(describe-salary a-user)

(def b-user {:first-name "john"
             :last-name "doe"
             :salary 1000})
(describe-salary b-user)
```

With `:keys`:

```clojure
(defn greet-user [{:keys [first-name last-name]}]
  (println first-name last-name))

(greet-user a-user)
```
