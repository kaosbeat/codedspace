(ns bossig.core
  (:use [overtone.live]
        [overtone.inst.piano]
        [overtone.inst.drum]
        [overtone.inst.io]
        [overtone.inst.synth]
         )
  )

(piano)

(definst kickA [freq 105 dur 1.2 width 0.5 amp -20 out-bus 0]
  (let [freq-env (* freq (env-gen (perc 0.02 (* 0.49 dur))))
        env (env-gen (perc 0.019 dur) 1 1 0 1 FREE)
        sqr (* (env-gen (perc 0 0.0800)) (pulse (* 2 freq) width))
        src (sin-osc freq-env)
        src2 (sin-osc-fb freq-env)
        filt (lpf (+ sqr src src2) 100)
        drum (+ sqr (* env filt))]

    (compander drum drum 0.2 1 0.1 0.01 0.01)
    )
  )
(kickA)
