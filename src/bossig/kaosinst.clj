(ns bossig.core)



(defn bassdrum [state ]
  (q/box (* 10 (get (:bd state) :note)) )
  )


(defn clicktrack [state]
  (q/stroke-weight 10)
  (q/stroke 100 50 210)
  (q/line (* (get (:ch state) :pan) 10) 500 1920 500 )
  )
