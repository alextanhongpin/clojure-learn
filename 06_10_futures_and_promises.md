# Future and Promises

```clojure
(defn long-calculation [num1 num2]
  (Thread/sleep 5000)
  (* num1 num2))

(defn long-run []
  (let [x (long-calculation 1 2)
        y (long-calculation 2 4)
        z (long-calculation 4 3)]
    (+ x y z)))

(time (long-run))

(defn fast-run []
  (let [x (future (long-calculation 1 2))
        y (future (long-calculation 2 4))
        z (future (long-calculation 4 3))]
    (+ @x @y @z)))

(time (fast-run))
```
