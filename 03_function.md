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
