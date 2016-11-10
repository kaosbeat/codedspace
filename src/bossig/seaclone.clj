(ns bossig.core)

(defn draw [state]
  (q/background  0 20 0)
;  (println (:lines state))
  (println (get (:chords state) :note ))
  (q/with-translation [50 50 0]

                                        ;(drawinplace @midichords state)

;    (wavelines 1000 5 2 3 1 200 1200 14 10 1000 100 10 )
    )

;(wavelines 500 3 6 4 22 0 1200 0 10 500 280 2 )


;  (addpill (* (* 2 (mod4)) (get (:ld1 state) :note)) (* 2 (get (:ld1 state) :velocity)) 10  1)
  (updatepills) ;;;pillstate is a bit boring at the moment....
  (q/with-translation [50 50 (* 0 (get (:ch state) :pan ))]
    (renderpills state)
    )



;(drawinplace @midichords state)

  (q/with-rotation [(* (tr) 7) 0 1 0 ])

  (dolines 0 -500 0 2000 2000 10 100 100 (get  (state :chords) :note) 10 0 255 0 235  )

  (.sendScreen @server))
