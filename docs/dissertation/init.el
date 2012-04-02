(require 'org-latex)
(org-babel-do-load-languages
 'org-babel-load-languages
 '((dot        . t)
   (emacs-lisp . t)
   (org        . t)
   (sh         . t)))
(add-to-list 'org-export-latex-classes
             '("dissertation"
               "\\documentclass{article}
\\usepackage[utf8]{inputenc}
\\usepackage[T1]{fontenc}
\\usepackage{geometry}
               \\usepackage{setspace}
               \\usepackage{wrapfig}
               \\usepackage{graphicx}
               \\usepackage{mathtools}
               \\usepackage{paralist}
               \\usepackage{longtable}
               \\usepackage{float}
               \\usepackage{wrapfig}
               \\usepackage{soul}
               \\usepackage{textcomp}
               \\usepackage{marvosym}
               \\usepackage{latexsym}
               \\usepackage{amssymb}
               \\usepackage{hyperref}
               \\tolerance=1000
               [NO-DEFAULT-PACKAGES]
               [NO-PACKAGES]"
               ("\\section{%s}" . "\\section*{%s}")
               ("\\subsection{%s}" . "\\subsection*{%s}")
               ("\\subsubsection{%s}" . "\\subsubsection*{%s}")
               ("\\paragraph{%s}" . "\\paragraph*{%s}")
               ("\\subparagraph{%s}" . "\\subparagraph*{%s}")))
