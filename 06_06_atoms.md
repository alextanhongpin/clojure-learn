# Atoms

```clojure
(def total-rows (atom 0))
@total-rows

; (reset! atom new-value)
; (swap! the-atom the-function & more-args)
(swap! total-rows + 100)
; (compare-and-set! the-atom old-value new-value)
```
