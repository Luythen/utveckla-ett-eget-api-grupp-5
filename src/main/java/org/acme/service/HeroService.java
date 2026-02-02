package org.acme.service;

import org.acme.model.Hero;
import org.acme.model.HeroDto;
import org.acme.model.HeroResponseDto;
import org.acme.model.Race;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
@Named
public class HeroService {

    @Inject
    EntityManager em;

    public HeroResponseDto createHero(HeroDto heroDto) throws IllegalArgumentException {

        Hero hero = new Hero();
            hero
            .
                .setRace            (heroDto.getRace())                
                .setFocusedFire     (isElf   (heroDto))
                .setSteadyFrame     (isDwarf (heroDto))
                .setStrongArms      (isOrc   (heroDto))
                .setJackOfAllTrades (isHuman (heroDto));

        em.persist(hero);

        HeroResponseDto heroResponseDto = new HeroResponseDto();
            heroResponseDto
                .setId              (hero.getId())
                .setRace            (hero.getRace())                
                .setFocusedFire     (hero.getFocusedFire())
                .setSteadyFrame     (hero.getSteadyFrame())
                .setStrongArms      (hero.getStrongArms())
                .setJackOfAllTrades (hero.getJackOfAllTrades());


        return heroResponseDto;
    }


    private boolean isElf   (HeroDto heroDto) { return heroDto.getRace() == Race.ELF;   }
    private boolean isDwarf (HeroDto heroDto) { return heroDto.getRace() == Race.DWARF; }
    private boolean isOrc   (HeroDto heroDto) { return heroDto.getRace() == Race.ORC;   }
    private boolean isHuman (HeroDto heroDto) { return heroDto.getRace() == Race.HUMAN; }



}
