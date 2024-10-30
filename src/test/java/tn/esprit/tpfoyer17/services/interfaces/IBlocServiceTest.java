package tn.esprit.tpfoyer17.services.interfaces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.services.impementations.BlocService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlocServiceTest {

    @Mock
    BlocRepository blocRepository;

    @InjectMocks
    BlocService blocService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void retrieveBlocs() {
        // Given
        List<Bloc> blocList = new ArrayList<>();
        blocList.add(new Bloc());
        blocList.add(new Bloc());

        when(blocRepository.findAll()).thenReturn(blocList);

        // When
        List<Bloc> result = blocService.retrieveBlocs();

        // Then
        assertEquals(2, result.size());
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    void addBloc() {
        // Given
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Test Bloc");

        when(blocRepository.save(bloc)).thenReturn(bloc);

        // When
        Bloc savedBloc = blocService.addBloc(bloc);

        // Then
        assertNotNull(savedBloc);
        assertEquals("Test Bloc", savedBloc.getNomBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void updateBloc() {
        // Given
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Updated Bloc");

        when(blocRepository.save(bloc)).thenReturn(bloc);

        // When
        Bloc updatedBloc = blocService.updateBloc(bloc);

        // Then
        assertNotNull(updatedBloc);
        assertEquals("Updated Bloc", updatedBloc.getNomBloc());
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    void retrieveBloc() {
        // Given
        long idBloc = 1L;
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc A");

        when(blocRepository.findById(idBloc)).thenReturn(Optional.of(bloc));

        // When
        Bloc result = blocService.retrieveBloc(idBloc);

        // Then
        assertNotNull(result);
        assertEquals("Bloc A", result.getNomBloc());
        verify(blocRepository, times(1)).findById(idBloc);
    }


    @Test
    void removeBloc() {
        // Given
        long idBloc = 1L;

        // When
        blocService.removeBloc(idBloc);

        // Then
        verify(blocRepository, times(1)).deleteById(idBloc);
    }

    @Test
    void findByFoyerIdFoyer() {
        // Given
        long idFoyer = 1L;
        List<Bloc> blocList = new ArrayList<>();
        blocList.add(new Bloc());

        when(blocRepository.findByFoyerIdFoyer(idFoyer)).thenReturn(blocList);

        // When
        List<Bloc> result = blocService.findByFoyerIdFoyer(idFoyer);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(blocRepository, times(1)).findByFoyerIdFoyer(idFoyer);
    }

    @Test
    void findByChambresIdChambre() {
        // Given
        Long idChambre = 1L;
        Bloc bloc = new Bloc();
        bloc.setNomBloc("Bloc B");

        when(blocRepository.findByChambresIdChambre(idChambre)).thenReturn(bloc);

        // When
        Bloc result = blocService.findByChambresIdChambre(idChambre);

        // Then
        assertNotNull(result);
        assertEquals("Bloc B", result.getNomBloc());
        verify(blocRepository, times(1)).findByChambresIdChambre(idChambre);
    }
}
