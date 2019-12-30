# clojure-learn

## Installation

```bash
$ brew install clojure
```

Start REPL:

```bash
$ clojure
```

Run script:

```bash
$ clojure main.clj
```


Testing:
```clojure
(ns test.com
  (:require [clojure.test :refer :all]))
```


## Text Processing

```clojure
(def sentence "this is a very long sentence this is good")
(def out (frequencies (.split sentence " ")))

(defn sort-keys [key1 key2]
  (compare [(get-in out key2) key2]
           [(get-in out key1) key1]))

(take 3 (keys (into (sorted-map-by sort-keys) out)))
```
