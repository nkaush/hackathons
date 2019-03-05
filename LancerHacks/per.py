'''uPER = (1 / minutes_played) *
     [ 3_points
     + (2/3) * assists
     + (2 - factor * (team_assits / team_field_goals)) * field_goals
     + (free_throws *0.5 * (1 + (1 - (team_assists / team_field_goals)) + (2/3) * (team_assists / team_field_goals)))
     - VOP * turnover_percentage
     - VOP * defensive_rebound% * (FG_attempts - FG)
     - VOP * 0.44 * (0.44 + (0.56 * DRB%)) * (FTA - FT)
     + VOP * (1 - DRB%) * (total_rebounds - offensive_rebounds)
     + VOP * DRB% * offensive_rebounds
     + VOP * steals
     + VOP * DRB% * blocks
     - personal_fouls * ((lg_FT / lg_PF) - 0.44 * (lg_FTA / lg_PF) * VOP) ]
factor = (2 / 3) - (0.5 * (lg_AST / lg_FG)) / (2 * (lg_FG / lg_FT))
VOP    = lg_PTS / (lg_FGA - lg_ORB + lg_turnovers + 0.44 * lg_FTA)
DRB%   = (league_totalrebounds - lg_offensiverebounds) / lg_totalrebounds
'''

def uPER(mp, threeP, lg_pts, pf, ast, fg, drb, drb_p, ft, fta, fga, stl, blk, team_ast, team_fg, lg_ft, lg_pf, lg_fta, lg_fga, lg_orb, lg_tov, lg_trb):
     factor = (2/3) - (0.5 * (lg_ast / lg_fg)) / (2* (lg_fg / lg_ft))
     vop = lg_pts / (lg_fga - lg_orb + lg_tov + 0.44 * lg_fta)
     drb_p = (lg_trb - lg_orb) / lg_trb
     PE = (1/mp) * (threeP + (2/3) * (ast) + (2 - factor * (team_ast/team_fg)) * fg + (ft * 0.5 * (1 + (1 - (team_ast / team_fg)) + (2/3) * (team_ast / team_fg))) - vop * tov - vop * drb_p * (fga - fg) - vop * 0.44 * (0.44 + (0.56 * drb_p)) * (fta - ft) + vop * (1 - drb_p) * (trb - orb) + vop * drb_p * orb + vop * stl + vop * drb_p * blk - pf * ((lg_ft / lg_pf) - 0.44 * (lg_fta / lg_pf) * vop))
     return PE