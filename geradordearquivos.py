import csv
import random
from datetime import datetime, timedelta

# Lista de tipos de arquivo
tipos_arquivo = ["pdf", "jpg", "txt", "doc", "xlsx", "mp3", "png", "zip"]

# Função para gerar uma data aleatória dentro de um intervalo de dias a partir de uma data base
def gerar_data_aleatoria(base, min_dias, max_dias):
    delta = timedelta(days=random.randint(min_dias, max_dias))
    return base + delta

# Gerar dados aleatórios
dados = []
for _ in range(2000):  # Defina o número de linhas desejado (2000 neste exemplo)
    nome = f"{''.join(random.choices('abcdefghijklmnopqrstuvwxyz', k=5))}.{''.join(random.choices('abcdefghijklmnopqrstuvwxyz', k=3))}"
    tipo = random.choice(tipos_arquivo)
    data_base = datetime(2020, 1, 1)  # Data base para gerar datas de modificação e criação
    data_modificacao = gerar_data_aleatoria(data_base, 0, 365)  # Intervalo de até 365 dias antes da data base
    data_criacao = gerar_data_aleatoria(data_base, 0, 365)  # Intervalo de até 365 dias antes da data base
    tamanho_kb = random.randint(1, 1024)  # Tamanho aleatório entre 1 KB e 1024 KB
    dados.append([nome, tipo, data_modificacao, data_criacao, tamanho_kb])

# Embaralhar os dados
random.shuffle(dados)

# Escrever os dados em um arquivo CSV
with open("data/data.csv", "w", newline="") as arquivo_csv:
    writer = csv.writer(arquivo_csv)
    writer.writerow(["nome", "tipo", "data_modificacao", "data_criacao", "tamanho_kb"])  # Cabeçalho
    writer.writerows(dados)

print("Arquivo CSV gerado com sucesso!")
