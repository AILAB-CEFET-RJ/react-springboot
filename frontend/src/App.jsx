import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function App() {
  const [clientes, setClientes] = useState([]);
  const [form, setForm] = useState({ nome: '', cpf: '', telefone: '' });

  const fetchClientes = async () => {
    const res = await axios.get('/api/v1/clientes');
    setClientes(res.data);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await axios.post('/api/v1/clientes', form);
    setForm({ nome: '', cpf: '', telefone: '' });
    fetchClientes();
  };

  useEffect(() => {
    fetchClientes();
  }, []);

  return (
    <div style={{ maxWidth: 600, margin: '2rem auto', fontFamily: 'sans-serif' }}>
      <h1>Cadastro de Clientes</h1>
      <form onSubmit={handleSubmit}>
        <input
          placeholder="Nome"
          value={form.nome}
          onChange={(e) => setForm({ ...form, nome: e.target.value })}
          required
        />
        <input
          placeholder="CPF"
          value={form.cpf}
          onChange={(e) => setForm({ ...form, cpf: e.target.value })}
          required
        />
        <input
          placeholder="Telefone"
          value={form.telefone}
          onChange={(e) => setForm({ ...form, telefone: e.target.value })}
          required
        />
        <button type="submit">Salvar</button>
      </form>

      <h2>Lista de Clientes</h2>
      <ul>
        {clientes.map((c) => (
          <li key={c.id}>
            <strong>{c.nome}</strong> - {c.telefone}
          </li>
        ))}
      </ul>
    </div>
  );
}