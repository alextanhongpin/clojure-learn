# Map Reduce

Naive implementation:
```clojure
(defn parse-line [line]
  (let [tokens (.split (.toLowerCase line) " ")]
    (map #(vector % 1) tokens)))

(defn combine [mapped]
  (->> (apply concat mapped)
       (group-by first)
       (map (fn [[k v]]
              {k (map second v)}))
       (apply merge-with conj)))

(defn sum [[k v]]
  {k (apply + v)})

(defn reduce-parsed-lines [collected-values]
  (apply merge (map sum collected-values)))

(defn word-frequency [filename]
  (->> (clojure.java.io/reader filename)
       (line-seq)
       (map parse-line)
       (combine)
       (reduce-parsed-lines)))

(word-frequency "./sum_41_pieces.txt")
```

Map-reduce word frequency:

```clojure
(defn combine [mapped]
  (->> (apply concat mapped)
       (group-by first)
       (map (fn [[k v]]
              {k (map second v)}))
       (apply merge-with conj)))

(defn map-reduce [mapper reducer args-seq]
  (->> (map mapper args-seq)
       (combine)
       (reducer)))

(defn word-frequency [filename]
  (map-reduce parse-line reduce-parsed-lines (line-seq (clojure.java.io/reader filename))))

(word-frequency "./sum_41_pieces.txt")
```

Map-reduce average line length:

```clojure
(defn map-reduce [mapper reducer args-seq]
  (->> (map mapper args-seq)
       (combine)
       (reducer)))

(def IGNORE "_")

(defn parse-line [line]
  (let [tokens (.split (.toLowerCase line) " ")]
    [[IGNORE (count tokens)]]))

(defn average [numbers]
  (/ (apply + numbers)
     (count numbers)))

(defn reducer [combined]
  (average (val (first combined))))

(defn average-line-length [filename]
  (map-reduce parse-line reducer (line-seq (clojure.java.io/reader filename))))

(average-line-length "./sum_41_pieces.txt")
```


