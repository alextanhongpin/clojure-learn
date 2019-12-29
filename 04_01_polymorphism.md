# Polymorphism


Without multimethod:

```clojure
(defn fee-amount [percentage user]
  (float (* 0.01 percentage (:salary user))))

(defn affiliate-fee-cond [user]
  (cond
    (= :google.com (:referrer user)) (fee-amount 0.01 user)
    (= :mint.com (:referrer user)) (fee-amount 0.3 user)
    :default (fee-amount 0.02 user)))
```

With multimethod:
```clojure
; (defmulti name dispatch-fn & options)
(defmulti affiliate-fee :referrer :default :else) ; Changing the default value to :else.

(defmethod affiliate-fee :google.com [user]
  (fee-amount 0.03 user))

(defmethod affiliate-fee :mint.com [user]
  (fee-amount 0.01 user))

(defmethod affiliate-fee :else [user]
  (fee-amount 0.02 user))

(def user-1 {:login "rob" :referrer :mint.com :salary 10000})
(def user-2 {:login "kyle" :referrer :google.com :salary 8000})
(def user-3 {:login "celeste" :referrer :yahoo.com :salary 70000})

(affiliate-fee user-1)
(affiliate-fee user-2)
(affiliate-fee user-3)
```

## Multiple dispatch


```clojure
; The double-colon is there to fully qualify keywords with your current namespace.
; user=> :foo
; :foo
; user=> ::foo
; :user/foo
(defn profit-rating [user]
  (let [ratings [::bronze ::silver ::gold ::platinum]]
    (nth ratings (rand-int (count ratings)))))

; Affiliate fee business rule
; affiliate | profit rating | fee (% of salary)
; mint.com | bronze | 0.03
; mint.com | silver | 0.04
; mint.com | gold/platinum | 0.05
; google.com | gold/platinum | 0.03

(defn fee-category [user]
  [(:referrer user) (profit-rating user)])

(defmulti profit-based-affiliate-fee fee-category)

(defmethod profit-based-affiliate-fee [:mint.com ::bronze] [user]
  (fee-amount 0.03 user))

(defmethod profit-based-affiliate-fee [:mint.com ::silver] [user]
  (fee-amount 0.04 user))

(defmethod profit-based-affiliate-fee [:mint.com ::gold] [user]
  (fee-amount 0.05 user))

(defmethod profit-based-affiliate-fee [:mint.com ::platinum] [user]
  (fee-amount 0.05 user))

(defmethod profit-based-affiliate-fee [:google.com ::gold] [user]
  (fee-amount 0.03 user))

(defmethod profit-based-affiliate-fee [:google.com ::platinum] [user]
  (fee-amount 0.03 user))

(defmethod profit-based-affiliate-fee :default [user]
  (fee-amount 0.02 user))
```

## Ad-hoc hierachies

```clojure
; (derive tag parent)
(derive ::bronze ::basic)
(derive ::silver ::basic)
(derive ::gold ::premier)
(derive ::platinum ::premier)

(isa? ::bronze ::basic)
(isa? ::platinum ::premier)

(defmulti affiliate-fee-for-hierachy fee-category)

(defmethod affiliate-fee-for-hierachy [:mint.com ::bronze] [user]
  (fee-amount 0.03 user))

(defmethod affiliate-fee-for-hierachy [:mint.com ::silver] [user]
  (fee-amount 0.04 user))

(defmethod affiliate-fee-for-hierachy [:mint.com ::premier] [user]
  (fee-amount 0.03 user))

(defmethod affiliate-fee-for-hierachy [:google.com ::premier] [user]
  (fee-amount 0.03 user))

(defmethod affiliate-fee-for-hierachy :default [user]
  (fee-amount 0.02 user))
```
