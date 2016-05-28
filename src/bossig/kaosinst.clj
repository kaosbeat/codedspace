(ns bossig.core)



(defn bassdrum [state ]
  (q/stroke 255)
  (q/fill (* 2 (get ( :ch state) :pan )) 10 10 )
  (q/box (* 10 (get (:bd state) :note)) )
  )


(defn clicktrack [state]
  (q/stroke-weight (get (:sd state) :velocity))
  (q/stroke 100 50 210)
  (q/line (* (get (:ch state) :pan) 10) (* 1200 (q/noise (q/random 0.5))) 1920 (* 5000 (q/noise (q/random 20))) )
  )
