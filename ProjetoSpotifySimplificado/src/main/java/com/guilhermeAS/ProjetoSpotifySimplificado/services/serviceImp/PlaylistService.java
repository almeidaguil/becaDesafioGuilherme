package com.guilhermeAS.ProjetoSpotifySimplificado.services.serviceImp;

import com.guilhermeAS.ProjetoSpotifySimplificado.domains.Playlist;
import com.guilhermeAS.ProjetoSpotifySimplificado.exceptions.ExceptionHandle;
import com.guilhermeAS.ProjetoSpotifySimplificado.repositories.PlaylistRepository;
import com.guilhermeAS.ProjetoSpotifySimplificado.services.InterfacePlaylist;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlaylistService implements InterfacePlaylist {

    private final ArtistaService artistaService;

    private final PlaylistRepository playlistRepository;

    @Override
    public Playlist criarPlaylist(@NotNull Playlist nomePlaylist) {
        if (nomePlaylist.getNome().length() <= 0){
            throw new RuntimeException("Você não inseriu o nome da playlist!");
        }

        return playlistRepository.save(nomePlaylist);
    }

    @Override
    public Playlist atualizarPlaylist (@NotNull Playlist nomePlaylist, Long id){
        Playlist atualizar = playlistRepository.findByNome(nomePlaylist.getNome());

        for(int i =0; i <= nomePlaylist.getMusica().size()-1;i++ )
            atualizar.getMusica().add(nomePlaylist.getMusica().get(i));

        playlistRepository.save(atualizar);

        return atualizar;
    }

    @Override
    public void apagarPlaylsit(Long id){
        playlistRepository.deleteById(id);
    }

    @Override
    public List<Playlist> listar(){

        return playlistRepository.findAll();
    }

    @Override
    public Playlist selecionar(Long id){
       Optional<Playlist> escolher = playlistRepository.findById(id);

        if (escolher.isPresent()){
            return escolher.get();
        }

        throw new RuntimeException("Playlist não localizada!! ");

    }

}
