# Macro Basis

```clojure
(def a-ref (ref 0))

(defmacro sync-set [r v]
  (list 'dosync
        (list 'ref-set r v)))

(sync-set a-ref 1)

@a-ref
```

With template:
```clojure
(defmacro sync-set-2 [r v]
  `(dosync
     (ref-set ~r ~v)))

(sync-set-2 a-ref 100)
@a-ref
```


## Unless

This will not work, since all the arguments are evaluated:
```clojure
(defn unless [test then]
  (if (not test)
    then))

(defn exhibits-oddity? [x]
  (unless (even? x)
          (println "very odd, indeed!")))
```

This will work, but we have to define an anonymous function:

```clojure
(defn unless [test then-thunk]
  (if (not test)
    (then-thunk)))

(defn exhibits-oddity? [x]
  (unless (even? x)
          #(println "very odd, indeed")))
```

Using `macro`:

```clojure
(defmacro unless [test then]
  `(if (not ~test)
     ~then))

(macroexpand '(unless (even? x) (println "hello world")))
```

Handling multiple expressions:

```clojure
(defmacro unless [test & exprs]
  `(if (not ~test)
     (do ~@exprs)))

(defn exhibits-oddity? [x]
  (unless (even? x)
            (println "odd")
            (println "very odd")))

(macroexpand '(unless (even? x) (println "odd") (println "very odd")))
```

## Generating name

Preventing variable capture with the `#` macro:

```clojure
(defmacro def-logged-fn [fn-name args & body]
  `(defn ~fn-name ~args
     (let [now# (System/currentTimeMillis)]
       (println "[" now# "] call to" (str (var ~fn-name)))
       ~@body)))

(def-logged-fn printname [name]
               (println "called function"))
(printname "john")

(macroexpand-1 '(def-logged-fn printname [name]
                               (println "hi" name)))
```

